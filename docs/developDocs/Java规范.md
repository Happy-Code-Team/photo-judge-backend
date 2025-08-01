# Java 注释规范

## 在注释中加入代码
`@code` 和 `<pre>` 标签结合：

将 `@code` 块嵌套在 `<pre>` 标签内。`@code` 确保了代码块内的文本会被视为代码（例如，不会对 <、> 进行 HTML 解析），而 `<pre>` 
标签则可以保留格式（如缩进和换行），这对于显示 JSON 结构非常有用。比如下面的写法。
```java
	/**
	 * 新增或更新定时任务。
	 * 根据传入的 Job 配置对象，调度或修改 Quartz 任务。
	 *
	 * 参考输入 JSON (请求体示例):
	 * <pre>{@code
	 * {
	 * "concurrent": "1",          // 是否允许任务并发执行 (0:不允许, 1:允许)
	 * "cronExpression": "0/10 * * * * ?", // Cron 表达式，定义任务执行周期 (例如: "0/10 * * * * ?" 表示每10秒执行一次)
	 * "invokeTarget": "testTask.execute()", // 任务调用的目标字符串 (Spring Bean名称.方法名())
	 * "jobGroup": "mysqlGroup",   // 任务所属的组名
	 * "jobId": 9,                 // 任务ID (数据库主键，用于标识任务)
	 * "jobName": "新增 mysqlJob 任务", // 任务的名称
	 * "misfirePolicy": "1",       // 错失执行策略 (0:立即执行, 1:忽略, 2:重复执行)
	 * "remark": "",               // 任务备注信息
	 * "status": "0"               // 任务状态 (0:正常, 1:暂停)
	 * }
	 * }</pre>
	 *
	 * @param job 包含任务配置信息的 Job 实体对象 (例如 JobDto 或 JobConfig)
	 * @throws Exception 如果任务调度或保存过程中发生错误
	 */
```

# Java 包名规范

包名应该是小写，并且通常使用反向域名作为前缀。不采用驼峰命名。类名首字母大写，采用驼峰命名法。

增加一个包名检查脚本，可以用来检查是否有不合法的包名。因为包名大小写不一致在某些情况下可能不会报错。
```python
"""检查包名 python 函数"""

# 可以尝试增加一个多线程的检查，提高检查的效率

import os


def check_package_case(root_dir, output_file):
    print(f"开始检查目录: {root_dir}")
    print(f"结果将写入文件: {output_file}")

    with open(output_file, "w", encoding="utf-8") as out_file:
        # dirpath 文件路径名
        # filename 文件名
        for dirpath, _, filenames in os.walk(root_dir):
            for filename in filenames:
                if not (filename.endswith(".java")):
                    continue
                file_path = os.path.join(dirpath, filename)

                try:
                    with open(file_path, "r", encoding="utf-8") as file:
                        package_name_declared = None
                        # 尝试读取文件前几行以找到 package 声明
                        for line_num, line in enumerate(file):
                            stripped_line = line.strip()
                            if stripped_line.startswith("package "):
                                # 提取声明的包名，去除 'package ' 和 ';'
                                package_name_declared = (
                                    stripped_line[len("package ") :]
                                    .strip()
                                    .replace(";", "")
                                )
                                break
                            # 避免读取整个大文件，如果前20行没找到就跳过
                            if line_num > 20:
                                continue

                        if package_name_declared:
                            # 获取相对路径，dirpath 是完整路径，root_dir 是 dirpath 的一半，计算出剩下的一半路径内容，
                            # 从 com. 开头的内容
                            relative_dir_path = os.path.relpath(dirpath, root_dir)
                            actual_package_from_path = relative_dir_path.replace(
                                os.path.sep, "."
                            )

                            # print(f"package_name_declared 是: {package_name_declared}")
                            # print(
                            #     f"actual_package_from_path 是: {actual_package_from_path}"
                            # )

                            # check1: 包名和文件位置是否大小写一致，某些情况下，即使不一致也不会报错, 但是应尽量避免
                            if (
                                package_name_declared.lower()
                                == actual_package_from_path.lower()
                            ) and (package_name_declared != actual_package_from_path):
                                out_file.write(
                                    f"包名与路径大小写不匹配：{file_path}\n"
                                    f"  声明包名: {package_name_declared}\n"
                                    f"  实际路径对应包名: {actual_package_from_path}\n\n"
                                )

                            # check2: 检查文件名是否没有采用全小写的格式
                            if not package_name_declared.islower():
                                out_file.write(
                                    f"包名没有采用全小写的格式：{file_path}\n"
                                    f"  声明包名: {package_name_declared}\n\n"
                                )

                except UnicodeDecodeError:
                    out_file.write(
                        f"警告: 无法以 UTF-8 编码读取文件 {file_path}，可能编码不正确。\n"
                    )
                except Exception as e:
                    out_file.write(f"处理文件 {file_path} 时发生未知错误: {e}\n")


# --- 使用示例 ---
# 请将 source_root 设置为你的 Java 源代码的根目录，通常是 src/main/java 或 src/test/java
# 例如：如果你的 Java 文件在 /your/project/src/main/java/com/example/MyClass.java
# 那么 source_root 应该是 "/your/project/src/main/java"
# source_root = "/Users/zhangguowei/code/dianzhong/glory/glory-admin/dzmf/src/main/java"
source_root = "/Users/zhangguowei/code/git-repo/photo-judge-backend/src/main/java"
output_file = "package_case_mismatch_report.txt"

check_package_case(source_root, output_file)

print(f"检查完成。不匹配的包名和路径信息已输出到 {output_file} 文件中。")

```
