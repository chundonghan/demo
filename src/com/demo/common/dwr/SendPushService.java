package com.demo.common.dwr;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;

public class SendPushService
{
    // 发送消息
    public void send(final String content)
    {
        Runnable run = new Runnable()
        {
            private ScriptBuffer script = new ScriptBuffer();

            public void run()
            {
                // 设置要调用的 js及参数
                script.appendCall("show", content);
                // 得到所有ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                // 遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions)
                {
                    scriptSession.addScript(script);
                }
            }
        };
        // 执行推送
        Browser.withAllSessions(run);
    }
}
