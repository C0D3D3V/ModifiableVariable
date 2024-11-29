/*
 * ModifiableVariable - A Variable Concept for Runtime Modifications
 *
 * Ruhr University Bochum, Paderborn University, Technology Innovation Institute, and Hackmanit GmbH
 *
 * Licensed under Apache License 2.0 http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.modifiablevariable.singlebyte;

import de.rub.nds.modifiablevariable.VariableModification;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.Random;

@XmlRootElement
@XmlType(propOrder = {"explicitValue", "modificationFilter"})
public class ByteExplicitValueModification extends VariableModification<Byte> {

    private static final int MAX_EXPLICIT_MODIFIER = 16;

    private Byte explicitValue;

    public ByteExplicitValueModification() {
        super();
    }

    public ByteExplicitValueModification(Byte explicitValue) {
        super();
        this.explicitValue = explicitValue;
    }

    @Override
    protected Byte modifyImplementationHook(Byte input) {
        return explicitValue;
    }

    public Byte getExplicitValue() {
        return explicitValue;
    }

    public void setExplicitValue(Byte explicitValue) {
        this.explicitValue = explicitValue;
    }

    @Override
    public VariableModification<Byte> getModifiedCopy() {
        Random r = new Random();
        if (r.nextBoolean()) {
            return new ByteExplicitValueModification(
                    (byte) (explicitValue + r.nextInt(MAX_EXPLICIT_MODIFIER)));
        } else {
            return new ByteExplicitValueModification(
                    (byte) (explicitValue - r.nextInt(MAX_EXPLICIT_MODIFIER)));
        }
    }

    @Override
    public VariableModification<Byte> createCopy() {
        return new ByteAddModification(explicitValue);
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
        ByteExplicitValueModification other = (ByteExplicitValueModification) obj;
        return Objects.equals(explicitValue, other.explicitValue);
    }
}
