package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "auto")
public class Auto {

    @XmlAttribute
    private boolean isOld;

    @XmlAttribute
    private int power;

    @XmlAttribute
    private String brand;
    private Contact contact;

    @XmlElementWrapper
    @XmlElement(name = "spec")
    private String[] specifications;

    public Auto() {
    }

    public Auto(boolean isOld, int power, String brand, Contact contact, String... specifications) {
        this.isOld = isOld;
        this.power = power;
        this.brand = brand;
        this.specifications = specifications;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "isOld=" + isOld
                + ", power=" + power
                + ", brand='" + brand + '\''
                + ", contact=" + contact
                + ", specifications=" + Arrays.toString(specifications)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Auto kiaCeed = new Auto(true, 129, "Kia",
                new Contact("(123) 456-78-90"),
                "черный металлик", "автомат", "бензин", "хэтчбек 5 дв.");

        JAXBContext context = JAXBContext.newInstance(Auto.class);
        /* serialization */
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(kiaCeed, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* deserialization */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Auto result = (Auto) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
