/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.string;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlRootElement
@XmlType(propOrder = "index")
@XmlAccessorType(XmlAccessType.FIELD)
public class StringExplicitValueFromFileModification extends StringExplicitValueModification {
    private int index;

    public StringExplicitValueFromFileModification() {
        super();
    }

    public StringExplicitValueFromFileModification(int index, String explicitValue) {
        super(explicitValue);
        this.index = index;
    }

    public StringExplicitValueFromFileModification(StringExplicitValueFromFileModification other) {
        super(other);
        index = other.index;
    }

    @Override
    public StringExplicitValueFromFileModification createCopy() {
        return new StringExplicitValueFromFileModification(this);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public VariableModification<String> getModifiedCopy() {
        throw new UnsupportedOperationException(
                "Cannot set modify Value of StringExplicitValueFromFileModification");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + index;
        hash = 31 * hash + explicitValue.hashCode();
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
        StringExplicitValueFromFileModification other =
                (StringExplicitValueFromFileModification) obj;
        if (index != other.index) {
            return false;
        }
        return Objects.equals(explicitValue, other.explicitValue);
    }
}
