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
import jakarta.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.Random;

@XmlRootElement
@XmlType(propOrder = {"factor", "modificationFilter"})
public class LongMultiplyModification extends VariableModification<Long> {

    private static final int MAX_FACTOR_MODIFIER = 256;

    private Long factor;

    public LongMultiplyModification() {}

    public LongMultiplyModification(Long factor) {
        this.factor = factor;
    }

    @Override
    protected Long modifyImplementationHook(final Long input) {
        return (input == null) ? 0L : input * factor;
    }

    public Long getFactor() {
        return factor;
    }

    public void setFactor(Long factor) {
        this.factor = factor;
    }

    @Override
    public VariableModification<Long> getModifiedCopy() {
        return new LongMultiplyModification(factor + new Random().nextInt(MAX_FACTOR_MODIFIER));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 48 * hash + Objects.hashCode(this.factor);
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
        final LongMultiplyModification other = (LongMultiplyModification) obj;
        return Objects.equals(this.factor, other.factor);
    }
}
