Auhor: Dmitry Kurinskiy(name.alari@gmail.com) and Svyat Podmogayev (s.podmogayev@gmail.com).

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
    roles {
        user {
            permissions = [
                    "Entity:*:admin",
                    "Oragnization:12:delete"
            ]
        }
    }
    
    // Replacement default views with owns one
    auth {
        // for authentication
        signIn {
            view = "/alterSignIn"
        }
        // for registration
        signUp {
            view = "/alterSignUp"
        }
        // for access denied indication 
        unauthorized {
            view = "/alterUnauthorized"
        }
    }
}
```
