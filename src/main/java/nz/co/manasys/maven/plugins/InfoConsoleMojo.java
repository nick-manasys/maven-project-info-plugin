package nz.co.manasys.maven.plugins;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


// import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.maven.project.MavenProject;

/**
 * Goal which displays project information or saves it to a file.
 *
 * @goal console
 * 
 * @phase process-sources
 */
@Mojo( name = "info",  defaultPhase = LifecyclePhase.COMPILE, threadSafe = true )
public class InfoConsoleMojo
    extends AbstractMojo
    // implements Contextualizable
{
    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    MavenProject project;

    public void execute()
        throws MojoExecutionException
    {
        File f = outputDirectory;

        if ( !f.exists() )
        {
            f.mkdirs();
        }

        File info = new File( f, "info.txt" );

        FileWriter w = null;
        try
        {
            w = new FileWriter( info );

            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print(new Date().toString());
            System.out.print("\n");
            System.out.print("groupId: ");
            System.out.print(project.getGroupId());
            System.out.print("\n");
            System.out.print("artifactId: ");
            System.out.print(project.getArtifactId());
            System.out.print("\n");
            System.out.print("description: ");
            System.out.print(null==project.getDescription()?"":project.getDescription());
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating file " + info, e );
        }
        finally
        {
            if ( w != null )
            {
                try
                {
                    w.close();
                }
                catch ( IOException e )
                {
                    // ignore
                }
            }
        }
    }
}
