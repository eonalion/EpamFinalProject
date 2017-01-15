package by.suboch.command;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 */
public enum CommandType {
    LOGIN {
        {
            this.command = new LogInCommand();
            this.visitorRole = EnumSet.of(VisitorRole.GUEST);
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
            this.visitorRole = EnumSet.of(VisitorRole.GUEST);
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ChangeLocaleCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER, VisitorRole.GUEST);
        }
    },
    ADDNEW {
        {
            this.command = new CreateEntityCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN);
        }
    },
    ADDBONUS {
        {
            this.command = new CreateBonusCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN);
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER);
        }
    };

    public IServletCommand command;
    public Set<VisitorRole> visitorRole;

    public IServletCommand getCurrentCommand() {
        return command;
    }
}
