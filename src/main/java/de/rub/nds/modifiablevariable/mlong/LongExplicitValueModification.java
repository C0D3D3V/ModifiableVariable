/**
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Copyright 2014-2017 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.mlong;

import de.rub.nds.modifiablevariable.VariableModification;
import java.util.Random;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "explicitValue", "modificationFilter", "postModification" })
public class LongExplicitValueModification extends VariableModification<Long> {

    private final static int MAX_EXPLICIT_MODIFIER = 256;

    private Long explicitValue;

    public LongExplicitValueModification() {

    }

    public LongExplicitValueModification(Long bi) {
        this.explicitValue = bi;
    }

    @Override
    protected Long modifyImplementationHook(final Long input) {
        return explicitValue;
    }

    public Long getExplicitValue() {
        return explicitValue;
    }

    public void setExplicitValue(Long explicitValue) {
        this.explicitValue = explicitValue;
    }

    @Override
    public VariableModification<Long> getModifiedCopy() {
        Random r = new Random();
        if (r.nextBoolean()) {
            return new LongExplicitValueModification(explicitValue + r.nextInt(MAX_EXPLICIT_MODIFIER));
        } else {
            return new LongExplicitValueModification(explicitValue - r.nextInt(MAX_EXPLICIT_MODIFIER));
        }
    }
}
