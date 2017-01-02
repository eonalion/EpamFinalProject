package com.suboch.command;

/**
 *
 */
public enum CommandType {
    LOGIN {
        {
            this.command = new SignInCommand();
        }
    },
    REGISTER {
        {
            this.command = new SignUpCommand();
        }
    };

    IServletCommand command;

    public IServletCommand getCurrentCommand() {
        return command;
    }
}
