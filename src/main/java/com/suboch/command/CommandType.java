package com.suboch.command;

/**
 *
 */
public enum CommandType {
    LOGIN {
        {
            this.command = new LogInCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLanguageCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    };

    private IServletCommand command;

    public IServletCommand getCurrentCommand() {
        return command;
    }
}
