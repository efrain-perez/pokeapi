pipeline {
    agent any

    parameters {
        string(name: 'container-version', defaultValue: 'latest', description: 'Version for the container.')
    }
    tools {
        maven 'Maven 3.6.0'
        jdk 'jdk8'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building project'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing'
                sh 'mvn clean test'
            }
        }
        stage('Build Container') {
            steps {
                echo 'Building docker container.'
                sh 'docker build -t efrainperez/pokeapi:${container-version}'
            }
        }
    }
}