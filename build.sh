#!/bin/bash
###############################################################################
#编译脚本的原理是将编译结果放到output目录中，这个样例模版提供一个产生
#jar包的最基本的编译脚本，对于特殊的需求请酌情考虑
#1、需要有一个control文件在工作目录的根目录
#2、该脚本支持参数化，参数将传入build_package函数（内容为最终执行的编译命令）
#，用$1,$2....表示，第1,2...个参数

###############用户修改部分################
readonly PACKAGE_DIR_NAME="./"    #如果在pom文件中定义了模块，请设置该变量,可选项
readonly DEPENDENCY_PACKAGE_DIR_NAME="iot-admin-portal.master"    #如果在pom文件中定义了模块，请设置该变量,可选项
readonly PACKAGE_JAR_NAME="iot-admin-console-0.0.1-SNAPSHOT.jar"    #定义产出的jar包名,必填项
#最终的抽包路径为$OUTPUT
###########################################

if [[ "${PACKAGE_JAR_NAME}" == "" ]];then
    echo "Please set "PACKAGE_JAR_NAME" value"
    exit 1
fi

function set_work_dir
{
    readonly OUTPUT=$(pwd)/output
    readonly WORKSPACE_DIR=$(pwd)
}

#清理编译构建目录操作
function clean_before_build
{
    cd ${WORKSPACE_DIR}
    mvn clean
    rm -rf ${OUTPUT}
}

#实际的编译命令
#这个函数中可使用$1,$2...获取第1,2...个参数
function build_package()
{
    cd ${WORKSPACE_DIR}
    mvn clean install -Dmaven.test.skip=false -Dmaven.javadoc.skip=true || return 1
}

function build_dependency_package()
{
    cd ${WORKSPACE_DIR}/${DEPENDENCY_PACKAGE_DIR_NAME}
    npm i || return 1
    npm run build || return 1
}


#建立最终发布的目录
function build_dir
{
    mkdir ${OUTPUT} || return 1
    mkdir ${OUTPUT}/bin || return 1
    mkdir ${OUTPUT}/logs || return 1
}

function dir_not_empty()
{
    if [[ ! -d $1 ]];then
        return 1
    fi
    if [[ $(ls $1|wc -l) -eq 0 ]];then
        return 1
    fi
    return 0
}

#拷贝编译结果到发布的目录
function copy_result
{
    cd ${WORKSPACE_DIR}
    cp -r ./${PACKAGE_DIR_NAME}/target/${PACKAGE_JAR_NAME} ${OUTPUT}/bin/${PACKAGE_JAR_NAME} || return 1
    cp -r ./control ${OUTPUT}/bin || return 1
   #如果有其他需要拷贝的文件，可以在这里添加
}

function copy_dependency_result
{
    cd ${WORKSPACE_DIR}/${DEPENDENCY_PACKAGE_DIR_NAME}
    mkdir ${WORKSPACE_DIR}/src/main/resources/static/
    cp -r build/* ${WORKSPACE_DIR}/src/main/resources/static/
}

#执行
function main()
{
    cd $(dirname $0)
    set_work_dir

    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Cleaning...'
    clean_before_build || exit 1
    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Clean completed'
    echo

    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Building...'
    build_dependency_package $@ || exit 1
    copy_dependency_result || exit 1
    build_package $@ || exit 1
    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Build completed'
    echo

    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Making dir...'
    build_dir || exit 1
    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Make completed'
    echo

    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Copy result to publish dir...'
    copy_result || exit 1
    echo "At: "$(date "+%Y-%m-%d %H:%M:%S") 'Copy completed'
    echo

    exit 0
}



main $@
