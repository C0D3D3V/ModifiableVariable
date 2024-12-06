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
@XmlType(propOrder = {"explicitValue", "modificationFilter"})
public class IntegerExplicitValueModification extends VariableModification<Integer> {

    private static final int MAX_VALUE_MODIFIER = 256;

    protected Integer explicitValue;

    public IntegerExplicitValueModification() {
        super();
    }

    public IntegerExplicitValueModification(Integer explicitValue) {
        super();
        this.explicitValue = explicitValue;
    }

    public IntegerExplicitValueModification(IntegerExplicitValueModification other) {
        super(other);
        explicitValue = other.explicitValue;
    }

    @Override
    public IntegerExplicitValueModification createCopy() {
        return new IntegerExplicitValueModification(this);
    }

    @Override
    protected Integer modifyImplementationHook(Integer input) {
        return explicitValue;
    }

    public Integer getExplicitValue() {
        return explicitValue;
    }

    public void setExplicitValue(Integer explicitValue) {
        this.explicitValue = explicitValue;
    }

    @Override
    public VariableModification<Integer> getModifiedCopy() {
        Random r = new Random();
        if (r.nextBoolean()) {
            return new IntegerExplicitValueModification(
                    explicitValue + r.nextInt(MAX_VALUE_MODIFIER));
        } else {
            return new IntegerExplicitValueModification(
                    explicitValue - r.nextInt(MAX_VALUE_MODIFIER));
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + explicitValue;
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
        IntegerExplicitValueModification other = (IntegerExplicitValueModification) obj;
        return Objects.equals(explicitValue, other.explicitValue);
    }

    @Override
    public String toString() {
        return "IntegerExplicitValueModification{" + "explicitValue=" + explicitValue + '}';
    }
}
