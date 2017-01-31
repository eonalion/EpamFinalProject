package by.suboch.command;

import by.suboch.command.admin.*;
import by.suboch.command.guest.LogInCommand;
import by.suboch.command.guest.RegisterCommand;
import by.suboch.command.user.*;
import by.suboch.entity.Visitor;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 */
public enum CommandType {
    LOG_IN {
        {
            this.command = new LogInCommand();
            this.role = EnumSet.of(Visitor.Role.GUEST);
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
            this.role = EnumSet.of(Visitor.Role.GUEST);
        }
    },
    CHANGE_LANGUAGE {
        {
            this.command = new ChangeLocaleCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER, Visitor.Role.GUEST);
        }
    },
    ADD_BONUS {
        {
            this.command = new AddBonusCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN);
        }
    },
    ADD_ARTIST {
        {
            this.command = new AddArtistCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN);
        }
    },
    ADD_ALBUM {
        {
            this.command = new AddAlbumCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN);
        }
    },
    ADD_TRACK{
        {
            this.command = new AddTrackCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN);
        }
    },
    ADD_TO_CART {
        {
            this.command = new AddToCartCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    REMOVE_FROM_CART {
        {
            this.command = new RemoveFromCart();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    CHANGE_PERSONAL_INFO {
        {
            this.command = new ChangePersonalInfoCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    CHANGE_LOGIN {
        {
            this.command = new ChangeLoginCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    CHANGE_EMAIL {
        {
            this.command = new ChangeEmailCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    CHANGE_AVATAR {
        {
            this.command = new ChangeAvatarCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    LOAD_IMAGE {
        {
            this.command = new LoadImageCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    LOAD_TRACK {
        {
            this.command = new LoadTrackCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    SHOW_ELEMENT {
        {
            this.command = new ShowElementCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    SWITCH_PAGE {
        {
            this.command = new SwitchPageCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    },
    LOG_OUT {
        {
            this.command = new LogOutCommand();
            this.role = EnumSet.of(Visitor.Role.ADMIN, Visitor.Role.USER);
        }
    };

    public IServletCommand command;
    public Set<Visitor.Role> role;

    public IServletCommand getCurrentCommand() {
        return command;
    }
}
