package moe._47saikyo;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class License extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161025238038061025283398101604081905261002f91610198565b600080546001600160a01b038088166001600160a01b031992831617909255600180548784169216821790556002805463ffffffff858116600160c01b0263ffffffff60c01b19918816600160a01b026001600160c01b03199093169589169590951791909117169290921790915560405163332bc20160e21b815263ccaf0804906100ce9030906004016001600160a01b0391909116815260200190565b600060405180830381600087803b1580156100e857600080fd5b505af11580156100fc573d6000803e3d6000fd5b505060025460405163332bc20160e21b81523060048201526001600160a01b03909116925063ccaf08049150602401600060405180830381600087803b15801561014557600080fd5b505af1158015610159573d6000803e3d6000fd5b505050505050505050610205565b6001600160a01b038116811461017c57600080fd5b50565b805163ffffffff8116811461019357600080fd5b919050565b600080600080600060a086880312156101b057600080fd5b85516101bb81610167565b60208701519095506101cc81610167565b60408701519094506101dd81610167565b92506101eb6060870161017f565b91506101f96080870161017f565b90509295509295909350565b603f806102136000396000f3fe6080604052600080fdfea26469706673582212204e75dde6f9ab8ea23ec5bf55a9364005f3607b8ccbf787386a1ae16a1a2d7b9764736f6c63430008190033";

    @Deprecated
    protected License(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected License(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected License(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected License(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Deprecated
    public static License load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new License(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static License load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new License(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static License load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new License(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static License load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new License(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String aut, String own, String rig, BigInteger exp, BigInteger iss) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, aut), 
                new org.web3j.abi.datatypes.Address(160, own), 
                new org.web3j.abi.datatypes.Address(160, rig), 
                new org.web3j.abi.datatypes.generated.Uint32(exp), 
                new org.web3j.abi.datatypes.generated.Uint32(iss)));
        return deployRemoteCall(License.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String aut, String own, String rig, BigInteger exp, BigInteger iss) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, aut), 
                new org.web3j.abi.datatypes.Address(160, own), 
                new org.web3j.abi.datatypes.Address(160, rig), 
                new org.web3j.abi.datatypes.generated.Uint32(exp), 
                new org.web3j.abi.datatypes.generated.Uint32(iss)));
        return deployRemoteCall(License.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String aut, String own, String rig, BigInteger exp, BigInteger iss) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, aut), 
                new org.web3j.abi.datatypes.Address(160, own), 
                new org.web3j.abi.datatypes.Address(160, rig), 
                new org.web3j.abi.datatypes.generated.Uint32(exp), 
                new org.web3j.abi.datatypes.generated.Uint32(iss)));
        return deployRemoteCall(License.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String aut, String own, String rig, BigInteger exp, BigInteger iss) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, aut), 
                new org.web3j.abi.datatypes.Address(160, own), 
                new org.web3j.abi.datatypes.Address(160, rig), 
                new org.web3j.abi.datatypes.generated.Uint32(exp), 
                new org.web3j.abi.datatypes.generated.Uint32(iss)));
        return deployRemoteCall(License.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
