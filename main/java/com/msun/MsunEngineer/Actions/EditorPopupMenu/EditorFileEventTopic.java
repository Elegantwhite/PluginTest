package com.msun.MsunEngineer.Actions.EditorPopupMenu;

// EditorFileEventTopic.java

import com.intellij.util.messages.Topic;

public interface EditorFileEventTopic {
    Topic<EditorFileEventTopic> TOPIC = Topic.create("MyEditorFileEventTopic", EditorFileEventTopic.class);

    void onFileSelected(String filePath);
}
