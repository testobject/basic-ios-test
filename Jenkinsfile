#!groovy

def runTest() {
    node {
        stage("checkout") {
            checkout scm
        }
        stage("test") {
            try {
                docker.image("java:8").inside("-v $HOME/.gradle:/root/.grade -v $HOME/.m2:/.m2") {
                    sh "./gradlew clean test -i"
                }
            } finally {
                junit "**/test-results/test/TEST-*.xml"
            }
        }
    }
}


try {
    runTest()
    if (env.SUCCESS_NOTIFICATION_ENABLED) {
        slackSend channel: "#${env.SLACK_CHANNEL}", color: "good", message: "`${env.JOB_BASE_NAME}` passed (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
    }
} catch (err) {
    if (env.FAILURE_NOTIFICATION_ENABLED) {
        slackSend channel: "#${env.SLACK_CHANNEL}", color: "bad", message: "`${env.JOB_BASE_NAME}` failed: $err (<${BUILD_URL}|open>)", teamDomain: "${env.SLACK_SUBDOMAIN}", token: "${env.SLACK_TOKEN}"
    }
    throw err
}
