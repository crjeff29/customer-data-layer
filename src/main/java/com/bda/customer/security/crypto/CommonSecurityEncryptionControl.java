package com.bda.customer.security.crypto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonSecurityEncryptionControl implements Serializable {
    private static final long serialVersionUID = -9079904529781056796L;
    private String encriptionKey;
    private String encriptionSalt;
    private String encriptionIv;
    private String encriptionCipher;
    private String encriptionSecretKeyFactory;
    private String encriptionInstance;
    private Integer iterations;
    private Integer lengthKey;

    public CommonSecurityEncryptionControl(String encriptionKey, String encriptionIv, String encriptionCipher, String encriptionInstance) {
        this.encriptionKey = encriptionKey;
        this.encriptionSalt = null;
        this.encriptionIv = encriptionIv;
        this.encriptionCipher = encriptionCipher;
        this.encriptionSecretKeyFactory = null;
        this.encriptionInstance = encriptionInstance;
        this.iterations = null;
        this.lengthKey = null;
    }

    public static ComSecEncriptacionControlDtoBuilder builder() {
        return new ComSecEncriptacionControlDtoBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CommonSecurityEncryptionControl)) {
            return false;
        } else {
            CommonSecurityEncryptionControl other = (CommonSecurityEncryptionControl) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label107:
                {
                    Object this$iterations = this.getIterations();
                    Object other$iterations = other.getIterations();
                    if (this$iterations == null) {
                        if (other$iterations == null) {
                            break label107;
                        }
                    } else if (this$iterations.equals(other$iterations)) {
                        break label107;
                    }

                    return false;
                }

                Object this$lengthKey = this.getLengthKey();
                Object other$lengthKey = other.getLengthKey();
                if (this$lengthKey == null) {
                    if (other$lengthKey != null) {
                        return false;
                    }
                } else if (!this$lengthKey.equals(other$lengthKey)) {
                    return false;
                }

                Object this$encriptionKey = this.getEncriptionKey();
                Object other$encriptionKey = other.getEncriptionKey();
                if (this$encriptionKey == null) {
                    if (other$encriptionKey != null) {
                        return false;
                    }
                } else if (!this$encriptionKey.equals(other$encriptionKey)) {
                    return false;
                }

                label86:
                {
                    Object this$encriptionSalt = this.getEncriptionSalt();
                    Object other$encriptionSalt = other.getEncriptionSalt();
                    if (this$encriptionSalt == null) {
                        if (other$encriptionSalt == null) {
                            break label86;
                        }
                    } else if (this$encriptionSalt.equals(other$encriptionSalt)) {
                        break label86;
                    }

                    return false;
                }

                label79:
                {
                    Object this$encriptionIv = this.getEncriptionIv();
                    Object other$encriptionIv = other.getEncriptionIv();
                    if (this$encriptionIv == null) {
                        if (other$encriptionIv == null) {
                            break label79;
                        }
                    } else if (this$encriptionIv.equals(other$encriptionIv)) {
                        break label79;
                    }

                    return false;
                }

                label72:
                {
                    Object this$encriptionCipher = this.getEncriptionCipher();
                    Object other$encriptionCipher = other.getEncriptionCipher();
                    if (this$encriptionCipher == null) {
                        if (other$encriptionCipher == null) {
                            break label72;
                        }
                    } else if (this$encriptionCipher.equals(other$encriptionCipher)) {
                        break label72;
                    }

                    return false;
                }

                Object this$encriptionSecretKeyFactory = this.getEncriptionSecretKeyFactory();
                Object other$encriptionSecretKeyFactory = other.getEncriptionSecretKeyFactory();
                if (this$encriptionSecretKeyFactory == null) {
                    if (other$encriptionSecretKeyFactory != null) {
                        return false;
                    }
                } else if (!this$encriptionSecretKeyFactory.equals(other$encriptionSecretKeyFactory)) {
                    return false;
                }

                Object this$encriptionInstance = this.getEncriptionInstance();
                Object other$encriptionInstance = other.getEncriptionInstance();
                if (this$encriptionInstance == null) {
                    if (other$encriptionInstance != null) {
                        return false;
                    }
                } else if (!this$encriptionInstance.equals(other$encriptionInstance)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommonSecurityEncryptionControl;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $iterations = this.getIterations();
        result = result * 59 + ($iterations == null ? 43 : $iterations.hashCode());
        Object $lengthKey = this.getLengthKey();
        result = result * 59 + ($lengthKey == null ? 43 : $lengthKey.hashCode());
        Object $encriptionKey = this.getEncriptionKey();
        result = result * 59 + ($encriptionKey == null ? 43 : $encriptionKey.hashCode());
        Object $encriptionSalt = this.getEncriptionSalt();
        result = result * 59 + ($encriptionSalt == null ? 43 : $encriptionSalt.hashCode());
        Object $encriptionIv = this.getEncriptionIv();
        result = result * 59 + ($encriptionIv == null ? 43 : $encriptionIv.hashCode());
        Object $encriptionCipher = this.getEncriptionCipher();
        result = result * 59 + ($encriptionCipher == null ? 43 : $encriptionCipher.hashCode());
        Object $encriptionSecretKeyFactory = this.getEncriptionSecretKeyFactory();
        result = result * 59 + ($encriptionSecretKeyFactory == null ? 43 : $encriptionSecretKeyFactory.hashCode());
        Object $encriptionInstance = this.getEncriptionInstance();
        result = result * 59 + ($encriptionInstance == null ? 43 : $encriptionInstance.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getEncriptionKey();
        return "ComSecEncriptacionControlDto(encriptionKey=" + var10000 + ", encriptionSalt=" + this.getEncriptionSalt() + ", encriptionIv=" + this.getEncriptionIv() + ", encriptionCipher=" + this.getEncriptionCipher() + ", encriptionSecretKeyFactory=" + this.getEncriptionSecretKeyFactory() + ", encriptionInstance=" + this.getEncriptionInstance() + ", iterations=" + this.getIterations() + ", lengthKey=" + this.getLengthKey() + ")";
    }

    public CommonSecurityEncryptionControl(final String encriptionKey, final String encriptionSalt, final String encriptionIv, final String encriptionCipher, final String encriptionSecretKeyFactory, final String encriptionInstance, final Integer iterations, final Integer lengthKey) {
        this.encriptionKey = encriptionKey;
        this.encriptionSalt = encriptionSalt;
        this.encriptionIv = encriptionIv;
        this.encriptionCipher = encriptionCipher;
        this.encriptionSecretKeyFactory = encriptionSecretKeyFactory;
        this.encriptionInstance = encriptionInstance;
        this.iterations = iterations;
        this.lengthKey = lengthKey;
    }

    public CommonSecurityEncryptionControl() {
    }

    public static class ComSecEncriptacionControlDtoBuilder {
        private String encriptionKey;
        private String encriptionSalt;
        private String encriptionIv;
        private String encriptionCipher;
        private String encriptionSecretKeyFactory;
        private String encriptionInstance;
        private Integer iterations;
        private Integer lengthKey;

        ComSecEncriptacionControlDtoBuilder() {
        }

        public ComSecEncriptacionControlDtoBuilder encriptionKey(final String encriptionKey) {
            this.encriptionKey = encriptionKey;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder encriptionSalt(final String encriptionSalt) {
            this.encriptionSalt = encriptionSalt;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder encriptionIv(final String encriptionIv) {
            this.encriptionIv = encriptionIv;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder encriptionCipher(final String encriptionCipher) {
            this.encriptionCipher = encriptionCipher;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder encriptionSecretKeyFactory(final String encriptionSecretKeyFactory) {
            this.encriptionSecretKeyFactory = encriptionSecretKeyFactory;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder encriptionInstance(final String encriptionInstance) {
            this.encriptionInstance = encriptionInstance;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder iterations(final Integer iterations) {
            this.iterations = iterations;
            return this;
        }

        public ComSecEncriptacionControlDtoBuilder lengthKey(final Integer lengthKey) {
            this.lengthKey = lengthKey;
            return this;
        }

        public CommonSecurityEncryptionControl build() {
            return new CommonSecurityEncryptionControl(this.encriptionKey, this.encriptionSalt, this.encriptionIv, this.encriptionCipher, this.encriptionSecretKeyFactory, this.encriptionInstance, this.iterations, this.lengthKey);
        }

        public String toString() {
            return "ComSecEncriptacionControlDto.ComSecEncriptacionControlDtoBuilder(encriptionKey=" + this.encriptionKey + ", encriptionSalt=" + this.encriptionSalt + ", encriptionIv=" + this.encriptionIv + ", encriptionCipher=" + this.encriptionCipher + ", encriptionSecretKeyFactory=" + this.encriptionSecretKeyFactory + ", encriptionInstance=" + this.encriptionInstance + ", iterations=" + this.iterations + ", lengthKey=" + this.lengthKey + ")";
        }
    }
}
