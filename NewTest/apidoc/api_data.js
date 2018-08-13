define({ "api": [
  {
    "type": "post",
    "url": "/NewTest/score/add",
    "title": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Score",
            "optional": false,
            "field": "Score",
            "description": "<p>分数实体</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>消息说明</p>"
          }
        ]
      }
    },
    "version": "0.1.0",
    "name": "____",
    "filename": "src/main/java/com/szzt/smart/NewTest/controller/ScoreController.java",
    "group": "E__Temp_NewTest_src_main_java_com_szzt_smart_NewTest_controller_ScoreController_java",
    "groupTitle": "E__Temp_NewTest_src_main_java_com_szzt_smart_NewTest_controller_ScoreController_java"
  },
  {
    "type": "get",
    "url": "/NewTest/score/{id}",
    "title": "",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>分数id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Score",
            "optional": false,
            "field": "Score",
            "description": "<p>分数实体</p>"
          }
        ]
      }
    },
    "name": "getScore",
    "filename": "src/main/java/com/szzt/smart/NewTest/controller/ScoreController.java",
    "group": "E__Temp_NewTest_src_main_java_com_szzt_smart_NewTest_controller_ScoreController_java",
    "groupTitle": "E__Temp_NewTest_src_main_java_com_szzt_smart_NewTest_controller_ScoreController_java"
  },
  {
    "type": "get",
    "url": "/NewTest/user/{id}",
    "title": "",
    "version": "0.1.0",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户</p>"
          }
        ]
      }
    },
    "name": "getUser",
    "filename": "src/main/java/com/szzt/smart/NewTest/controller/UserController.java",
    "group": "E__Temp_NewTest_src_main_java_com_szzt_smart_NewTest_controller_UserController_java",
    "groupTitle": "E__Temp_NewTest_src_main_java_com_szzt_smart_NewTest_controller_UserController_java"
  }
] });
