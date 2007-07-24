/*
 * Created on Jul 23, 2007
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright @2007 the original author or authors.
 */
package org.fest.swing.junit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * Understands programmatic execution of a Ant task.
 *
 * @author Alex Ruiz
 */
public final class AntLauncher {

  private final Project project = new Project();
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();

  public AntLauncher(File buildFile) {
    project.addBuildListener(createLogger(out));
    project.init();
    ProjectHelper.configureProject(project, buildFile);
    String path = buildFile.getAbsolutePath();
    project.setUserProperty("ant.file", path);
  }

  public String executeDefaultTarget() {
    return executeTarget(project.getDefaultTarget());
  }
  
  public String executeTarget(String targetName) {
    project.executeTarget(targetName);
    return out.toString();
  }
  
  private static BuildLogger createLogger(ByteArrayOutputStream out) {
    BuildLogger logger = new DefaultLogger();
    logger.setMessageOutputLevel(Project.MSG_INFO);
    logger.setOutputPrintStream(new PrintStream(out));
    logger.setErrorPrintStream(new PrintStream(out));
    logger.setEmacsMode(false);
    return logger;
  }
}
