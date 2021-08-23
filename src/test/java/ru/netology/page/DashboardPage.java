package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement transferFromSecondToFirstButton = $$("[data-test-id=action-deposit]").first();
    private SelenideElement transferFromFirstToSecondButton = $$("[data-test-id=action-deposit]").last();
    private static SelenideElement balance0001 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private static SelenideElement balance0002 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");

    public TransferPage chooseFirstCardToDeposit() {
        transferFromSecondToFirstButton.click();
        return new TransferPage();
    }

    public TransferPage chooseSecondCardToDeposit() {
        transferFromFirstToSecondButton.click();
        return new TransferPage();
    }

    public static int getFirstCardActualBalance() {
        String valueFirst = balance0001.getText();
        String firstCardBalance = valueFirst.substring(29, valueFirst.indexOf(" ", 29));
        return Integer.parseInt(firstCardBalance);
    }

    public static int getSecondCardActualBalance() {
        String valueSecond = balance0002.getText();
        String secondCardBalance = valueSecond.substring(29, valueSecond.indexOf(" ", 29));
        return Integer.parseInt(secondCardBalance);
    }
}
