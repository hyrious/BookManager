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

    class Main
      SQLite db
      ... # other variables or state
      Scene scene_login
      ... # other scenes
      start(window)
        db = SQLite.new
        scene_map = HashMap.new(id, Scene)
        
        loader_login = FXMLLoader.new("login.fxml")
        scene_login = Scene.new(loader_login.load())
        scene_map.put("login", scene_login)
        
        loader_login.getController<LoginController>().use(db, window, scene_map)
        loader_borrow.getController<LoginController>().use(db, window, scene_map)
        ...

        window.setScene(scene_map.get("login"))
        window.show()

都简写成这样了还是一堆冗余，我们来造个 DSL 吧

    scene :login
      layout
        VBox
          <%= %W[Database Username Password].map{|e| HBox(Label(e), TextField(e)) } %>
          HBox(Button("Connect"), Button("Exit"))
      button :connect
        db.connect $database, $username, $password
      button :exit
        window.close
