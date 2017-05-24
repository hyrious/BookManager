# 图书管理工具

**课程设计要求**  
> 
（1） 用户登录；  
（2） 用户管理；  
（3） 图书管理，包括借阅与归还；  
（4） 图书和用户信息查询。

## 数据库设计

    约定表/列命名全部使用英文小写

书（ID，书名，是否可借）
  * 可以存在多本一样名字的但编号不同的书

用户（ID，姓名，密码，权限）
  * 密码计算 hash 后存储
  * 权限的二进制位分别表示（左高右低）：管理用户|管理书|借阅书
  * 初始会创建一个超级管理员（不能借阅）

借阅表（用户ID，书ID，归还期限）
  * 该表动态维护，还书时自动从该表删除并向 log 表添加一条记录

记录（用户ID，书ID，时间戳）

## 代码结构设计

    class Main { // is the only Controller
      User user
      SQLite db
      // load scenes from FXML
      // methods as Controller
      login() {
        db.login(
          textFieldDatabase.getText(),
          textFieldUsername.getText(),
          textFieldPassword.getText()
        ) // db has changed states
      } rescue {
        // printStackTrace()
      }
    }

相当简化的结构。

也许可以出个 DSL。

    User
      ...
    SQLite
      ...
    Main
      User user
      SQLite db
      default Scene login { // #sceneLogin
        VBox {
          HBox { Label{text "Database"}; TextField(id "Database") }
          HBox { Label{text "Username"}; TextField(id "Username") }
          HBox { Label{text "Password"}; TextField(id "Password") }
          HBox { Button:login; Button:exit }
        }
      }
      Scene borrow { ... }
      Scene return { ... }
      loginLogin() { ... }
      loginExit() { ... }