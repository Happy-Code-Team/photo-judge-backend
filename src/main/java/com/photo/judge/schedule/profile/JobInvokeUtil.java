package com.photo.judge.schedule.profile;

import com.photo.judge.common.exception.CommonException;
import com.photo.judge.common.utils.spring.SpringUtils;
import com.photo.judge.model.entity.quartz.QuartzJob;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JobInvokeUtil {
	/**
	 * 执行方法, 获取 SpringBean，调用对应的 Bean 的方法
	 *
	 * @param job 系统任务
	 */
	public static void invokeMethod(QuartzJob job) throws Exception {
		String invokeTarget = job.getInvokeTarget();
		String beanName = getBeanName(invokeTarget, job);
		String methodName = getMethodName(invokeTarget, job);
		Object bean = SpringUtils.getBean(beanName);
		invokeMethod(bean, methodName, job);
	}

	/**
	 * 调用任务方法
	 *
	 * @param bean       目标对象
	 * @param methodName 方法名称
	 */
	private static void invokeMethod(Object bean, String methodName, QuartzJob job) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = bean.getClass().getDeclaredMethod(methodName, QuartzJob.class);
		method.invoke(bean, job);
	}

	/**
	 * 获取bean名称
	 *
	 * @param invokeTarget 目标字符串
	 * @return bean名称
	 */
	public static String getBeanName(String invokeTarget, QuartzJob job) {
		if (!org.springframework.util.StringUtils.hasLength(invokeTarget)) {
			throw new CommonException(job.getJobName() + ": BeanName 参数错误");
		}
		return invokeTarget.substring(0, invokeTarget.lastIndexOf("."));
	}

	/**
	 * 获取方法名称
	 *
	 * @param invokeTarget
	 * @param job
	 * @return method名称
	 */
	public static String getMethodName(String invokeTarget, QuartzJob job) {
		if (!org.springframework.util.StringUtils.hasLength(invokeTarget)) {
			throw new CommonException(job.getJobName() + ": MethodName 参数错误");
		}

		return invokeTarget.substring(invokeTarget.lastIndexOf(".") + 1, invokeTarget.indexOf("("));
	}
}
