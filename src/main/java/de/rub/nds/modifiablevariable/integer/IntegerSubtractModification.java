/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.integer;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.Random;

@XmlRootElement
@XmlType(propOrder = {"subtrahend", "modificationFilter"})
public class IntegerSubtractModification extends VariableModification<Integer> {

    private static final int MAX_SUBTRACT_MODIFIER = 256;

    private Integer subtrahend;

    public IntegerSubtractModification() {}

    public IntegerSubtractModification(Integer subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    protected Integer modifyImplementationHook(final Integer input) {
        return (input == null) ? -subtrahend : input - subtrahend;
    }

    public Integer getSubtrahend() {
        return subtrahend;
    }

    public void setSubtrahend(Integer subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public VariableModification<Integer> getModifiedCopy() {
        return new IntegerSubtractModification(
                subtrahend + new Random().nextInt(MAX_SUBTRACT_MODIFIER));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + subtrahend;
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
        final IntegerSubtractModification other = (IntegerSubtractModification) obj;
        return Objects.equals(this.subtrahend, other.subtrahend);
    }
}
