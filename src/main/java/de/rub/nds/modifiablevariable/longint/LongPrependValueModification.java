/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.longint;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class LongPrependValueModification extends VariableModification<Long> {

    private Long prependValue;

    public LongPrependValueModification() {
        super();
    }

    public LongPrependValueModification(Long prependValue) {
        super();
        this.prependValue = prependValue;
    }

    public LongPrependValueModification(LongPrependValueModification other) {
        super(other);
        prependValue = other.prependValue;
    }

    @Override
    public LongPrependValueModification createCopy() {
        return new LongPrependValueModification(this);
    }

    @Override
    protected Long modifyImplementationHook(Long input) {
        if (input == null) {
            input = 0L;
        }
        return prependValue << Long.SIZE - Long.numberOfLeadingZeros(input) | input;
    }

    public Long getPrependValue() {
        return prependValue;
    }

    public void setPrependValue(Long prependValue) {
        this.prependValue = prependValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(prependValue);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LongPrependValueModification other = (LongPrependValueModification) obj;
        return Objects.equals(prependValue, other.prependValue);
    }

    @Override
    public String toString() {
        return "LongPrependValueModification{" + "prependValue=" + prependValue + '}';
    }
}
