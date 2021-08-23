package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferNegativeTest {
    int transferOverLimit = 200000;
    @Test
    void shouldNotTransferOverLimit() {
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
        TransferPage.transferFromFirstToSecond(cardInfo, transferOverLimit);
        val firstCardBalanceAfter = DataHelper.getBalanceTransferMinus(firstCardBalanceBefore, transferOverLimit);
        val secondCardBalanceAfter = DataHelper.getBalanceTransferPlus(secondCardBalanceBefore, transferOverLimit);
        assertEquals(firstCardBalanceAfter, DashboardPage.getFirstCardActualBalance());
        assertEquals(secondCardBalanceAfter, DashboardPage.getSecondCardActualBalance());
    }
}
