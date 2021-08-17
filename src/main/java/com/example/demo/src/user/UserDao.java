package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into User (userEmail, userPassword, userNickname, termsAndConditions, personalInfo, financialTrans, aboveFourteen, benefitAlarm) VALUES (?,?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserEmail(), postUserReq.getUserPassword(), postUserReq.getUserNickname(), postUserReq.getTermsAndConditions(), postUserReq.getPersonalInfo(), postUserReq.getFinancialTrans(), postUserReq.getAboveFourteen(), postUserReq.getBenefitAlarm()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select userEmail from User where userEmail = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);
    }

    public User getPwd(PostLoginReq postLoginReq){
        String getPwdQuery = "select userIdx, userEmail, userName, userPassword, userNickname, userPhoneNum, userAddress, storeBoss, status from User where userEmail = ?";
        String getPwdParams = postLoginReq.getUserEmail();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("userEmail"),
                        rs.getString("userName"),
                        rs.getString("userPassword"),
                        rs.getString("userNickname"),
                        rs.getString("userPhoneNum"),
                        rs.getString("userAddress"),
                        rs.getInt("storeBoss"),
                        rs.getInt("status")
                ),
                getPwdParams
                );

    }

    public int checkQuitUser(String email) {
        String checkQuitUserQuery = "select exists(select userIdx from User where userEmail = ? AND User.status = 0)";
        String checkQuitUserParams = email;

        return this.jdbcTemplate.queryForObject(checkQuitUserQuery, int.class, checkQuitUserParams);
    }

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select userIdx, userEmail, userPhoneNum, userNickname from User where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userEmail"),
                        rs.getString("userPhoneNum"),
                        rs.getString("userNickname")),
                getUserParams);
    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update User set userNickname = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserNickname(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public int checkUserIdx(int userIdx) {
        String checkUserIdxQuery = "select exists(select userIdx from User where userIdx = ? AND User.status != 'N')";
        int checkUserIdxParams = userIdx;

        return this.jdbcTemplate.queryForObject(checkUserIdxQuery, int.class, checkUserIdxParams);
    }
}
