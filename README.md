Auhor: Dmitry Kurinskiy (name.alari@gmail.com) and Svyat Podmogayev (s.podmogayev@gmail.com).

**infra-auth** - Плагин, предоставляющий механизмы построения систем аутентификации и авторизации, 
а также другие возможности обеспечения безопасности для промышленных приложений.

Usage
--------------

For more flexible usage of the plugin you should override following classes:
.
..
...
, and define ..


Configuration
--------------

The plugin`s configs template:

```groovy
infra {
    // Replacement default views with owns one
    auth {

        defaultRolePermissions = ["*:*"]
        defaultRoleName = "user"

        signInView = "/alterSignIn"

        signUpView = "/alterSignUp"

        unauthorizedView = "/alterUnauthorized"
    }
}
```
