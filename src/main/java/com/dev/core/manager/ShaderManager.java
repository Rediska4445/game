package com.dev.core.manager;

import org.lwjgl.opengl.GL20;

public class ShaderManager {

    private final int programId;

    private int vertexShaderId;

    private int fragmentShaderId;

    public ShaderManager() throws Exception {
        programId = GL20.glCreateProgram();
        if (programId == 0) {
            throw new Exception("Could not create Shader");
        }
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderId = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderId = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
    }

    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = GL20.glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader. Type: " + shaderType);
        }

        GL20.glShaderSource(shaderId, shaderCode);
        GL20.glCompileShader(shaderId);

        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + GL20.glGetShaderInfoLog(shaderId, 1024));
        }

        GL20.glAttachShader(programId, shaderId);

        return shaderId;
    }

    public void link() throws Exception {
        GL20.glLinkProgram(programId);
        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + GL20.glGetProgramInfoLog(programId, 1024));
        }

        if (vertexShaderId != 0) {
            GL20.glDetachShader(programId, vertexShaderId);
        }
        if (fragmentShaderId != 0) {
            GL20.glDetachShader(programId, fragmentShaderId);
        }

        GL20.glValidateProgram(programId);
        if (GL20.glGetProgrami(programId, GL20.GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + GL20.glGetProgramInfoLog(programId, 1024));
        }

    }

    public void bind() {
        GL20.glUseProgram(programId);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programId != 0) {
            GL20.glDeleteProgram(programId);
        }
    }

//
//    private final int programID;
//    private int vertexShaderID, fragmentShaderID;
//
//    public ShaderManager() throws Exception {
//        programID = GL20.glCreateProgram();
//        if(programID == 0)
//            throw new Exception("Could not create shader");
//    }
//
//    public void createVertexShader(String shaderCode) throws Exception {
//        vertexShaderID = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
//    }
//
//    public void createFragmentShader(String shaderCode) throws Exception {
//        fragmentShaderID = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
//    }
//
//    public int createShader(String shaderCode, int shaderType) throws Exception {
//        int shaderID = GL20.glCreateShader(shaderType);
//        if(shaderID == 0)
//            throw new Exception("Error creating shader. Type: " + shaderType);
//
//        GL20.glShaderSource(shaderID, shaderCode);
//        GL20.glCompileShader(shaderID);
//
//        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0)
//            throw new Exception("Error compiling shader. Type: " + shaderType + "\nInfo: " + GL20.glGetShaderInfoLog(shaderID, 1024));
//
//        GL20.glAttachShader(programID, shaderID);
//
//        return shaderID;
//    }
//
//    public void link() throws Exception {
//        GL20.glLinkProgram(programID);
//        if(GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == 0)
//            throw new Exception("Error linking shader" + "\nInfo: " + GL20.glGetShaderInfoLog(programID, 1024));
//
//        if(vertexShaderID != 0)
//            GL20.glDetachShader(programID, vertexShaderID);
//
//        if(fragmentShaderID != 0)
//            GL20.glDetachShader(programID, fragmentShaderID);
//
//        GL20.glValidateProgram(programID);
//        if(GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == 0)
//            throw new Exception("Unable to validate shader code: " + GL20.glGetProgramInfoLog(programID, 1024));
//    }
//
//    public void bind() {
//        GL20.glUseProgram(programID);
//    }
//
//    public void unbind() {
//        GL20.glUseProgram(0);
//    }
//
//    public void cleanup() {
//        unbind();
//        if(programID != 0)
//            GL20.glDeleteProgram(programID);
//    }

}
