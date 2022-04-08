{\rtf1\ansi\ansicpg1252\cocoartf2638
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fnil\fcharset0 HelveticaNeue;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\deftab560
\pard\pardeftab560\slleading20\partightenfactor0

\f0\fs26 \cf0 pipeline \{\
\'a0\'a0agent \{\
\'a0\'a0\'a0\'a0label 'Slave_Mac'\
\'a0\'a0\}\
\
\'a0\'a0options \{\
\'a0\'a0\'a0\'a0	buildDiscarder(logRotator(numToKeepStr: '3'))\
\'a0	disableConcurrentBuilds()\
\'a0\'a0\}\
\
\'a0\'a0tools \{\
	jdk 'JDK8_Mac' //Versi\'f3n preinstalada en la Configuraci\'f3n del Master\
\'a0\'a0\}\
\
\'a0\'a0stages\{\
\'a0\'a0\
\'a0\'a0\'a0\'a0stage('Compile') \{\
\'a0\'a0\'a0\'a0\'a0\'a0steps \{\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0echo "------------>Compile<------------"\
\'a0\'a0\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0\}\'a0\
\
\'a0\'a0\'a0\'a0stage('Unit Tests') \{\
\'a0\'a0\'a0\'a0\'a0\'a0steps\{\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0echo "------------>Unit Tests<------------"\
\
\'a0\'a0\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0\}\
\
\'a0\'a0\'a0\'a0stage('Static Code Analysis') \{\
\'a0\'a0\'a0\'a0\'a0\'a0steps\{\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0echo '------------>An\'e1lisis de c\'f3digo est\'e1tico<------------'\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0withSonarQubeEnv('Sonar') \{\
sh "$\{tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'\}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0\}\
\
stage('Checkout') \{\
\'a0\'a0\'a0\'a0\'a0\'a0steps\{\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0echo "------------>Checkout<------------"\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0checkout scm\
\'a0\'a0\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0\}\
\
stage('Compile') \{\
\'a0\'a0steps \{\
    echo "------------>Compile<------------"\
\'a0\'a0\'a0\'a0sh 'chmod +x ./gradlew'\
\'a0\'a0\'a0\'a0sh './gradlew build -x test'\
\'a0\'a0\}\
\}\
\
stage('Unit Tests') \{\
\'a0\'a0\'a0\'a0\'a0\'a0steps\{\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0echo "------------>Unit Tests<------------"\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0sh './gradlew clean'\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0sh './gradlew test'\
\'a0\'a0\'a0\'a0\'a0\'a0\'a0\'a0sh './gradlew jacocoTestReport'\
    \}\
\'a0\}\
\
\
\
\}\
\
\'a0\'a0post \{\
\'a0\'a0\'a0\'a0always \{\
\'a0\'a0\'a0\'a0\'a0\'a0echo 'This will always run'\
\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0success \{\
\'a0\'a0\'a0\'a0\'a0\'a0echo 'This will run only if successful'\
\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0failure \{\
\'a0\'a0\'a0\'a0\'a0\'a0echo 'This will run only if failed'\
\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0unstable \{\
\'a0\'a0\'a0\'a0\'a0\'a0echo 'This will run only if the run was marked as unstable'\
\'a0\'a0\'a0\'a0\}\
\'a0\'a0\'a0\'a0changed \{\
	echo 'This will run only if the state of the Pipeline has changed'\
\'a0\'a0\'a0\'a0\'a0\'a0 echo 'For example, if the Pipeline was previously failing but is now successful'\
\'a0\'a0\'a0\'a0\}\
\'a0\'a0\}\
\}}