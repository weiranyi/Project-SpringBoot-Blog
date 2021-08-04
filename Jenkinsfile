// 获取当前环境信息
String buildNumber = env.BUILD_NUMBER; // 第几号构建，由Jenkins提供
String timestamp = new Date().format('yyyyMMddHHmmss');
String projectName = env.JOB_NAME.split(/\//)[0];
// e.g awesome-project/release/RELEASE-1.0.0
String branchName = env.JOB_NAME.split(/\//)[1..-1].join(/\//);

println("${buildNumber} ${timestamp} ${projectName}");

String version = "${buildNumber}-${timestamp}-${projectName}";

node {
    checkout scm;// 源代码管理系统

    if(params.BuildType=='Rollback') {
        return rollback()
    } else if(params.BuildType=='Normal'){
        return normalCIBuild(version)
    } else if(branchName == 'main'){
        // 促使化时候使用的操作
        setScmPollStrategyAndBuildTypes(['Normal', 'Rollback']);
    }
}

def normalCIBuild(String version) {
    stage 'test & package'

    sh('./mvnw clean package')

    stage('docker build')

    sh("docker build . -t 1.116.227.22:5000/weiranyi_springboot_blog:${version}")

    sh("docker push 1.116.227.22:5000/weiranyi_springboot_blog:${version}")

    stage('deploy')

    input 'deploy?'

    deployVersion(version)
}

def deployVersion(String version) {
    sh "ssh root@1.116.227.22 'docker rm -f xdml && docker run --name xdml -d -p 8080:8080 1.116.227.22:5000/weiranyi_springboot_blog:${version}'"
}

def setScmPollStrategyAndBuildTypes(List buildTypes) {
    def propertiesArray = [
            parameters([choice(choices: buildTypes.join('\n'), description: '', name: 'BuildType')]),
            // 每分钟去检查下源码仓库
            pipelineTriggers([[$class: "SCMTrigger", scmpoll_spec: "* * * * *"]])
    ];
    properties(propertiesArray);
}

def rollback() {
    def dockerRegistryHost = "http://1.116.227.22:5000";
    def getAllTagsUri = "/v2/weiranyi_springboot_blog/tags/list";

    def responseJson = new URL("${dockerRegistryHost}${getAllTagsUri}")
            .getText(requestProperties: ['Content-Type': "application/json"]);

    println(responseJson)

    // {name:xxx,tags:[tag1,tag2,...]}
    Map response = new groovy.json.JsonSlurperClassic().parseText(responseJson) as Map;

    def versionsStr = response.tags.join('\n');

    def rollbackVersion = input(
            message: 'Select a version to rollback',
            ok: 'OK',
            parameters: [choice(choices: versionsStr, description: 'version', name: 'version')])

    println rollbackVersion
    deployVersion(rollbackVersion)
}