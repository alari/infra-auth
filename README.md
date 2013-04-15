Auhor: Dmitry Kurinskiy(name.alari@gmail.com) and Svyat Podmogayev (s.podmogayev@gmail.com).

**infra-auth** - Плагин, предоставляющий механизмы построения систем аутентификации и авторизации, 
а также другие возможности обеспечения безопасности для промышленных приложений.

Usage
--------------


Configuration
--------------

The plugin`s configs template:

`
infra {
    
    roles {
        user {
            permissions = [
                    "Entity:*:admin",
                    "Oragnization:12:delete"
            ]
        }
    }
    
    // Replacement default views with own ones
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
`
