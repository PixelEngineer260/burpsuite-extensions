/**
 * @author Example Author <example@example.com>
 */

package burp;

public class BurpExtender implements IBurpExtender, IMessageEditorTabFactory
{
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
     
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();
        callbacks.setExtensionName("SAMLRequestHandler");
        callbacks.registerMessageEditorTabFactory(this);        
        IntruderProcessor proc = new IntruderProcessor(this.helpers);
        callbacks.registerHttpListener(proc);
    }


    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable)
    {
        return new SamlRequestTab(controller, editable, callbacks);
    }
}
