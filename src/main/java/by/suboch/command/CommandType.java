package by.suboch.command;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 */
public enum CommandType {
    LOG_IN {
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
    CHANGE_LANGUAGE {
        {
            this.command = new ChangeLocaleCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER, VisitorRole.GUEST);
        }
    },
    ADD_BONUS {
        {
            this.command = new AddBonusCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN);
        }
    },
    ADD_ARTIST {
        {
            this.command = new AddArtistCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN);
        }
    },
    FORWARD {
        {
            this.command = new ForwardCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER, VisitorRole.GUEST);
        }
    },
    CHANGE_PERSONAL_INFO {
        {
            this.command = new ChangePersonalInfoCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER);
        }
    },
    CHANGE_LOGIN {
        {
            this.command = new ChangeLoginCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER);
        }
    },
    CHANGE_EMAIL{
        {
            this.command = new ChangeEmailCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER);
        }
    },
    CHANGE_PASSWORD{
        {
            this.command = new ChangePasswordCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER);
        }
    },
    CHANGE_AVATAR {
        {
            this.command = new ChangeAvatarCommand();
            this.visitorRole = EnumSet.of(VisitorRole.ADMIN, VisitorRole.USER);
        }
    },
    LOG_OUT {
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
