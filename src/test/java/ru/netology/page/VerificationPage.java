package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeInput.shouldBe(Condition.visible);
    }

    public void validVerify(DataHelper.VerificationCode verificationCode) {
        codeInput.setValue(verificationCode.getCode());
        verifyButton.click();
    }
}
