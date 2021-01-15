#!groovy

String workspace = "C:\\Windows\\System32\\config\\systemprofile\\AppData\\Local\\Jenkins\\.jenkins\\workspace";
String sourceCodePath = "";
pipeline{
	agent {
		node{label "master" //指定运行节点的标签或者名称
			 customWorkspace "${workspace}" //指定运行工作目录（可选）
		}
	}
	options {
		timestamps() //日志内容会包含时间信息，需要安装Timestamper插件
		skipDefaultCheckout() //删除隐式checkout scm语句
		disableConcurrentBuilds() //禁止并行
		timeout(time:1,unit:'HOURS') //流水线超时时间设置为1h
	}
	stages {
		//下载代码
		stage("GetCode"){ //阶段名称
			steps{ //步骤
				timeout(time:5, unit:"MINUTES"){ //步骤超时时间
					script{ //填写运行代码
						println('获取代码');
					}
				}
			}
		}
		//代码扫描
		stage("ScanCode"){ //阶段名称
			steps{ //步骤
				timeout(time:20, unit:"MINUTES"){ //步骤超时时间
					script{ //填写运行代码
						println('扫描代码');
					}
				}
			}
		}
		//构建
		stage("Build"){ //阶段名称
			steps{ //步骤
				timeout(time:10, unit:"MINUTES"){ //步骤超时时间
					script{ //填写运行代码
						println('应用打包');
					}
				}
			}
		}
		//代码发布
		stage("Release"){ //阶段名称
			steps{ //步骤
				timeout(time:20, unit:"MINUTES"){ //步骤超时时间
					script{ //填写运行代码
						println('发布代码');
					}
				}
			}
		}
	}
	post{
		always{ //总是执行的脚本片段
			script {
				println('always');
			}
		}
		success{ //成功后执行
			script {
				currentBuild.description += "\n 构建成功"; //currentBuild是一个全局变量
			}
		}
		failure{ //失败后执行
			script {
				currentBuild.description += "\n 构建失败";
			}
		}
		aborted{ //取消后执行
			script {
				currentBuild.description += "\n 构建取消";
			}
		}
	}
}