#!/bin/bash
echo "开始更新项目..."

date=$(date)
# git submodule foreach git pull origin master
git pull origin master:master
git add -A
git commit -m "${message}${USER} 更新于 ${date}."
git push -u origin master

echo "更新并推送成功！"
exit 0;
