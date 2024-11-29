/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.biginteger;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

@XmlRootElement
@XmlType(propOrder = {"factor", "modificationFilter"})
@XmlAccessorType(XmlAccessType.FIELD)
public class BigIntegerMultiplyModification extends VariableModification<BigInteger> {

    private static final int MAX_FACTOR_LENGTH = 8;

    private BigInteger factor;

    public BigIntegerMultiplyModification() {
        super();
    }

    public BigIntegerMultiplyModification(BigInteger factor) {
        super();
        this.factor = factor;
    }

    @Override
    protected BigInteger modifyImplementationHook(BigInteger input) {
        return input == null ? BigInteger.ZERO : input.multiply(factor);
    }

    public BigInteger getFactor() {
        return factor;
    }

    public void setFactor(BigInteger factor) {
        this.factor = factor;
    }

    @Override
    public VariableModification<BigInteger> getModifiedCopy() {
        return new BigIntegerMultiplyModification(
                factor.add(new BigInteger(MAX_FACTOR_LENGTH, new Random())));
    }

    @Override
    public VariableModification<BigInteger> createCopy() {
        return new BigIntegerMultiplyModification(factor);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + factor.hashCode();
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
        BigIntegerMultiplyModification other = (BigIntegerMultiplyModification) obj;
        return Objects.equals(factor, other.factor);
    }
}
