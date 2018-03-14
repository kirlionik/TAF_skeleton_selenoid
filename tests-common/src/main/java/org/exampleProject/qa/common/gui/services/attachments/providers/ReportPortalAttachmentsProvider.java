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
package org.exampleProject.qa.common.gui.services.attachments.providers;

import lombok.extern.slf4j.Slf4j;
import org.exampleProject.qa.common.gui.services.webdriver.WrappedWebdriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Lazy
@Slf4j
public class ReportPortalAttachmentsProvider implements AttachmentsProvider {
    private static final String LOG_STRING_TEMPLATE = "RP_MESSAGE#BASE64#{}#{}";

    @Override
    public byte[] attachScreenshot() {
        return new byte[0];
    }

    @Override
    public byte[] attachScreenshot(String nameOfScreenshot) {
        return new byte[0];
    }

    @Override
    public String attachPageTitle() {
        return null;
    }

    public String attachText(String nameOfAttachment, String textOfAttachment) {
        if (textOfAttachment != null) {
            setAttachment(textOfAttachment, nameOfAttachment);
        } else {
            setAttachment("null", nameOfAttachment);
        }
        return "";
    }

    private void setAttachment(String text, String logMsg) {
        log.info(LOG_STRING_TEMPLATE,
                Base64.getEncoder().encodeToString(text.getBytes()),
                logMsg
        );
    }
}
