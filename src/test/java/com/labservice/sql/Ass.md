# 基本表
## people
```sql
    pid int 自动增长的表示列
    num varchar 学号
    pname text 人名
    plimit int 权限
    account varchar 账号
    passwd varchar 密码
    scid int 学校标识列
```
---
## project
```sql
    pnum varchar 项目编号
    proname 项目名称
    sid int 项目负责人id
    tid int 项目指导教师id
    status bit 审核状态（项目需要先进行提交，然后交由老师审核，创建时状态位0 ，教师审核过后位1）
    lid int 申请使用实验室的id
```
---
## school
```sql
    scid int 自动增长的表示列
    scname varchar 学校名字
```
---
## label
```sql
    同上
```
---
### ppl(people project label)
```sql
    pplid int 自动增长的表示列
    pid int 人员id
    pnum varchar 项目编号
    lid int 实验室id
    stat 状态位
```
(记录项目参与人员的记录某人参加某项目利用什么实验室,项目负责人是否通过参加申请)

---
### record
```sql
    pplid 
    retime datatime 记录时间
```
为记录项目参与人员的打卡记录，pplid为成员参与的信息，retime为打卡时间

---