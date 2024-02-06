#!/bin/sh

# Configure aws cli
configure_aws_cli()
{
aws configure set aws_access_key_id ${AWS_ACCESS_KEY_ID}
aws configure set aws_secret_access_key ${AWS_SECRET_ACCESS_KEY}
aws configure set region ${AWS_REGION}
}

# prepare required lambda resources
prepare_resources()
{
    # update nodejs and git
    apk add --update nodejs npm
    # Install serverless framework
    npm install -g serverless
}


# Script Entry
configure_aws_cli
prepare_resources


if [ "$1" == "deploy" ];then
    sls deploy --stage "$2"
elif [ "$1" == "destroy" ];then
    sls remove --stage "$2"
fi

