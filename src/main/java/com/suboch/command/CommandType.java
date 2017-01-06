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
    };

    IServletCommand command;

    public IServletCommand getCurrentCommand() {
        return command;
    }
}
