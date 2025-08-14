pipeline {
    agent any

    tools {
        maven 'Maven3' // Make sure Jenkins has this tool installed/configured
        jdk 'Java17'   // Change to match your project's Java version
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
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

    }


}
