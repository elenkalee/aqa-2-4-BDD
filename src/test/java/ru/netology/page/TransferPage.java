package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private static SelenideElement amountInput = $("[data-test-id=amount] input");
    private static SelenideElement fromInput = $("[data-test-id=from] input");
    private static SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public static void transferFromSecondToFirst(DataHelper.CardInfo cardInfo, int transfer) {
        amountInput.setValue(String.valueOf(transfer));
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public static void transferFromFirstToSecond(DataHelper.CardInfo cardInfo, int transfer) {
        amountInput.setValue(String.valueOf(transfer));
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }
}
