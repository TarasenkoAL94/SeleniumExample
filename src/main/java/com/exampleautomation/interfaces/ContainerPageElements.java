package com.exampleautomation.interfaces;

import org.openqa.selenium.By;

import java.util.Collections;

/**
 * <p>
 * Interface that should be implemented by Element enums in modal/full-page modal classes
 * </p><p>
 * Adds default {@link #chainedBy()} method that concatenates container/parent locator with specified/child element's locator.
 * </p><p>
 * Requires you to implement {@link #getContainerElement()}.
 * </p><p>
 * Child element locators should NOT have the dot before "//".
 * </p>
 */
public interface ContainerPageElements extends PageElements {

    /**
     * Override this method for each enum that implements ContainerPageElements.
     * Return value is a container/parent element that will have its locator prepended
     * before each element.
     * In order for parent locator to be prepended,
     * use {@link #chainedBy()} instead of {@link #by()} for child locator.
     *
     * @return Element.MODAL_CONTAINER;
     */
    <T extends PageElements> T getContainerElement();

    /**
     * This method needs to be invoked from child element.<br>
     * Example of the elements:
     * <pre>
     * MODAL_CONTAINER_PARENT(By.xpath(//container));
     * MODAL_ELEMENT_CHILD(By.xpath("//element"));
     * </pre>
     * Example of the usage:
     * <pre>
     * CommonUtility.scrollToAndClick(Element.MODAL_ELEMENT_CHILD.chainedBy());
     * </pre>
     *
     * @return single locator = container/parent locator + specified/child locator, e.g. MODAL_ELEMENT_CHILD:
     * <pre>By.xpath(//container//element)</pre>
     */
    default By chainedBy() {
        return chainedBy(Collections.emptyList());
    }

    /**
     * This method needs to be invoked from child element.<br>
     * Example of the elements:
     * <pre>
     * MODAL_CONTAINER_PARENT(By.xpath(//container[%s-%s]));
     * MODAL_ELEMENT_CHILD(By.xpath("//element[%s-%s]"));
     * MODAL_ELEMENT_CHILD_REPEATED(By.xpath("//element[%1$s-%2$s]"));</pre>
     * Example of the usages:
     * <pre>
     * CommonUtility.scrollToAndClick(Element.MODAL_ELEMENT_CHILD.chainedBy(containerArg1, containerArg2, arg1, arg2));
     * CommonUtility.scrollToAndClick(Element.MODAL_ELEMENT_CHILD_REPEATED.chainedBy(containerArg1, containerArg2));
     * </pre>
     *
     * @param args parameters for BOTH of specified locators in order (parent, then child)
     *             e.g. containerArg1, containerArg1, arg1, arg2.
     *             All argument types should have #toString() implemented.
     *
     * @return single locator = container/parent locator + specified/child locator, e.g.:<br>
     * MODAL_ELEMENT_CHILD: <pre>By.xpath(//container[containerArg1-containerArg2]//element[arg1-arg2])</pre>
     * MODAL_ELEMENT_CHILD_REPEATED: <pre>By.xpath(//container[containerArg1-containerArg2]//element[containerArg1-containerArg2])</pre>
     */
    default By chainedBy(Object... args) {
        String chainedLocatorTemplate = getContainerElement().getLocatorString() + this.getLocatorString();
        return By.xpath(String.format(chainedLocatorTemplate, args));
    }

}
