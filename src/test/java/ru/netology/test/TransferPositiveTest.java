package ru.netology.test;


import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferPositiveTest {
    int transfer = 700;

    @Test
    void shouldTransferFromSecondToFirstCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = new DashboardPage();
        verificationPage.validVerify(verificationCode);
        val firstCardBalanceBefore = DashboardPage.getFirstCardActualBalance();
        val secondCardBalanceBefore = DashboardPage.getSecondCardActualBalance();
        val transferPage = dashboardPage.chooseFirstCardToDeposit();
        val cardInfo = DataHelper.getSecondCardInfo();
        TransferPage.transferFromSecondToFirst(cardInfo, transfer);
        val firstCardBalanceAfter = DataHelper.getBalanceTransferPlus(firstCardBalanceBefore, transfer);
        val secondCardBalanceAfter = DataHelper.getBalanceTransferMinus(secondCardBalanceBefore, transfer);
        assertEquals(firstCardBalanceAfter, DashboardPage.getFirstCardActualBalance());
        assertEquals(secondCardBalanceAfter, DashboardPage.getSecondCardActualBalance());
    }

    @Test
    void shouldTransferFromFirstToSecondCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = new DashboardPage();
        verificationPage.validVerify(verificationCode);
        val firstCardBalanceBefore = DashboardPage.getFirstCardActualBalance();
        val secondCardBalanceBefore = DashboardPage.getSecondCardActualBalance();
        val transferPage = dashboardPage.chooseSecondCardToDeposit();
        val cardInfo = DataHelper.getFirstCardInfo();
        TransferPage.transferFromFirstToSecond(cardInfo, transfer);
        val firstCardBalanceAfter = DataHelper.getBalanceTransferMinus(firstCardBalanceBefore, transfer);
        val secondCardBalanceAfter = DataHelper.getBalanceTransferPlus(secondCardBalanceBefore, transfer);
        assertEquals(firstCardBalanceAfter, DashboardPage.getFirstCardActualBalance());
        assertEquals(secondCardBalanceAfter, DashboardPage.getSecondCardActualBalance());
    }
}
