/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.path;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/** Modification that appends a string to the original value. */
@XmlRootElement
@XmlType(propOrder = {"modificationFilter"})
public class PathToggleRootValueModification extends VariableModification<String> {

    public PathToggleRootValueModification() {
        super();
    }

    public PathToggleRootValueModification(PathToggleRootValueModification other) {
        super(other);
    }

    @Override
    public PathToggleRootValueModification createCopy() {
        return new PathToggleRootValueModification(this);
    }

    @Override
    protected String modifyImplementationHook(String input) {
        if (input == null) {
            return null;
        }
        if (!input.isEmpty() && input.charAt(0) == '/') {
            return input.substring(1);
        }
        return "/" + input;
    }

    @Override
    public VariableModification<String> getModifiedCopy() {
        return new PathToggleRootValueModification();
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return getClass() == obj.getClass();
    }
}
