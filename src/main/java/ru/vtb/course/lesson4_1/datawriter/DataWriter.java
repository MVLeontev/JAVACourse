package ru.vtb.course.lesson4_1.datawriter;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vtb.course.lesson4_1.AppConfig;
import ru.vtb.course.lesson4_1.datatransformer.DateTransformer;
import ru.vtb.course.lesson4_1.model.Model;
import ru.vtb.course.lesson4_1.repositories.Logins;
import ru.vtb.course.lesson4_1.repositories.LoginsRepo;
import ru.vtb.course.lesson4_1.repositories.Users;
import ru.vtb.course.lesson4_1.repositories.UsersRepo;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.Date;

@Component
public class DataWriter implements DataWritable<Model>{

    static final Logger logger = LoggerFactory.getLogger(DataWriter.class);
    UsersRepo usersRepo;
    LoginsRepo loginsRepo;

//    @Autowired
//    DataSource dataSource;

    @Autowired
    public void setUsersRepo(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }
    @Autowired
    public void setLoginsRepo(LoginsRepo loginsRepo) {
        this.loginsRepo = loginsRepo;
    }



    public void saveData(Model model) {

//        HikariDataSource hds = (HikariDataSource)dataSource;
//        hds.setJdbcUrl();
//        hds.setSchema();
//        hds.setUsername();
//        hds.setPassword();

        String parseUserName;
        String parseFio;
        String parseDateStr;
        String parseApp;
        Date parseDate;
        Users u;
        Logins l;
        for (String str : model.getDataArr()) {
            parseUserName = str.split(AppConfig.separator)[0];
            parseFio = str.split(AppConfig.separator)[1] + " " + str.split(AppConfig.separator)[2] + " " + str.split(AppConfig.separator)[3];
            parseDateStr = str.split(AppConfig.separator)[4];
            parseApp = str.split(AppConfig.separator)[5];
            try {
                parseDate = AppConfig.formatter.parse(parseDateStr);
            } catch (ParseException e) {
                //throw new RuntimeException(e + " - Illegal Date format in string: " + str);
                logger.error("Illegal Date format in string: " + str);
                continue;
            }

            u = usersRepo.findByUsername(parseUserName);
            if (u == null) {
                u = new Users();
                u.setUsername(parseUserName);
                u.setFio(parseFio);
                usersRepo.save(u);
            }
            l = new Logins();
            l.setUsers(u);
            l.setApplication(parseApp);
            l.setAccess_date(parseDate);
            loginsRepo.save(l);

        }

    }

}
