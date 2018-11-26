package org.exampleProject.qa.common.gui.services.pages;
/*
* Copyright 2002 - 2017 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
import lombok.val;
import one.util.streamex.StreamEx;
import org.exampleProject.qa.common.gui.models.SerpSnippet;
import org.exampleProject.qa.common.gui.annotations.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@PageObject
public class SerpPageObject extends AbstractPageObject{
    private final String snippetListLocator = ".//*[@id='ListViewInner']/li";
    private final String buttonActionLocator = ".//*[@id='cbelm']//a[text()='Auction']";
    private final String buttonBuyItNowLocator = ".//*[@id='cbelm']//a[text()='Buy It Now']";

    @FindBy(xpath = snippetListLocator)
    List<WebElement> snippetList;

    @FindBy(xpath = buttonActionLocator)
    List<WebElement> buttonAction;

    @FindBy(xpath = buttonBuyItNowLocator)
    List<WebElement> buttonBuyItNow;

    public List<SerpSnippet> getSnippets(){
        wdHelper.waitForElementIsPresent(By.xpath(snippetListLocator));
        return StreamEx.of(snippetList)
                .map(this::toSnippet)
                .toList();

    }
    public SerpSnippet toSnippet(WebElement webElement){
        val title = webElement.findElement(By.xpath(".//h3")).getText();
        return new SerpSnippet(title);
    }

    public boolean getButtonAction(){
        if(buttonAction.size()==1){
            return true;
        }else{
            return false;
        }
    }

    public boolean getButtonBuyItNow(){
        if(buttonBuyItNow.size()==1){
            return true;
        }else{
            return false;
        }
    }
}
