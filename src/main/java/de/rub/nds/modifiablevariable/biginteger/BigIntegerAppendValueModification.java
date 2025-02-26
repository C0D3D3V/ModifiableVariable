/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.biginteger;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.util.Objects;

@XmlRootElement
public class BigIntegerAppendValueModification extends VariableModification<BigInteger> {

    private BigInteger appendValue;

    public BigIntegerAppendValueModification() {
        super();
    }

    public BigIntegerAppendValueModification(BigInteger appendValue) {
        super();
        this.appendValue = appendValue;
    }

    public BigIntegerAppendValueModification(BigIntegerAppendValueModification other) {
        super(other);
        appendValue = other.appendValue;
    }

    @Override
    public BigIntegerAppendValueModification createCopy() {
        return new BigIntegerAppendValueModification(this);
    }

    @Override
    protected BigInteger modifyImplementationHook(BigInteger input) {
        if (input == null) {
            input = BigInteger.ZERO;
        }
        return input.shiftLeft(appendValue.bitLength()).or(appendValue);
    }

    public BigInteger getAppendValue() {
        return appendValue;
    }

    public void setAppendValue(BigInteger appendValue) {
        this.appendValue = appendValue;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(appendValue);
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
        BigIntegerAppendValueModification other = (BigIntegerAppendValueModification) obj;
        return Objects.equals(appendValue, other.appendValue);
    }

    @Override
    public String toString() {
        return "BigIntegerAppendValueModification{" + "appendValue=" + appendValue + '}';
    }
}
