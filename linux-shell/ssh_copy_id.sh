#!/bin/bash
# 自动拷贝ssh免密登录
# 前提条件
# 1.生成本机密钥   ssh-keygen
# 2.安装expect包  yum -y install expect
# 3.修改SERVERS为对应ip,密码(需要统一密码)
SERVERS="10.240.206.121 10.240.206.158 10.240.206.194"
PASSWORD=admin

auto_ssh_copy_id() {
    expect -c "set timeout 5;
        spawn ssh-copy-id $1;
        expect {
            *(yes/no)* {send -- yes\r;exp_continue;}
            *assword:* {send -- $2\r;exp_continue;}
            eof        {exit 0;}
        }";
}

ssh_copy_id_to_all() {
    for SERVER in $SERVERS
    do
        auto_ssh_copy_id $SERVER $PASSWORD
    done
}

ssh_copy_id_to_all