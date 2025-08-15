pipeline {
    agent (label: jenkins-agent)

    tools {
        maven 'Maven3' // Make sure Jenkins has this tool installed/configured
        jdk 'Java21'   // Change to match your project's Java version
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/jaabless/FakeStore_API.git'
            }
        }

        stage('Build & Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

    }

    post {
        always {
            echo "Generating and publishing Allure report..."
            bat 'allure generate target/allure-results --clean -o target/allure-report || exit /b 0'
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }

        success {
            slackSend(
                channel: 'jenkins_notify',
                color: 'good',
                message: "✅ Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' succeeded. (<${env.BUILD_URL}|Open>)"
            )
        }
        failure {
            slackSend(
                channel: 'jenkins_notify',
                color: 'danger',
                message: "❌ Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' failed. (<${env.BUILD_URL}|Open>)"
            )
        }
        unstable {
            slackSend(
                channel: 'jenkins_notify',
                color: 'warning',
                message: "⚠️ Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' is unstable. (<${env.BUILD_URL}|Open>)"
            )
        }
    }


}
